package core

object Config {
  final val awsAccessKey = System.getenv("AWS_ACCESS_KEY")
  final val awsSecretKey = System.getenv("AWS_SECRET_KEY")
  final val eccInstance = System.getenv("EC_INSTANCE")
}
