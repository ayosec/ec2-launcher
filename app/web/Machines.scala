package web

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.concurrent._
import play.api.libs.json.Json.toJson

import core._

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model._

object Machines extends Controller {

  val awsClient = new AmazonEC2Client(new BasicAWSCredentials(Config.awsAccessKey, Config.awsSecretKey))

  def instanceStatus =
    awsClient.
      describeInstances(new DescribeInstancesRequest {{ withInstanceIds(Config.eccInstance) }}).
      getReservations.get(0).
      getInstances.get(0)

  def status = Action {
    Async {
      Akka.future { instanceStatus } map { result =>
        Ok(toJson(Map("state" -> result.getState.getName, "ip" -> result.getPublicIpAddress)))
      }
    }
  }

  def start = Action {
    Async {
      Akka.future {
        awsClient.
          startInstances(new StartInstancesRequest {{ withInstanceIds(Config.eccInstance) }})
      } map { result =>
        Ok(toJson(Map("result" -> result.toString)))
      }
    }
  }

  def stop = Action {
    Async {
      Akka.future {
        awsClient.
          stopInstances(new StopInstancesRequest {{ withInstanceIds(Config.eccInstance) }})
      } map { result =>
        Ok(toJson(Map("result" -> result.toString)))
      }
    }
  }

}
