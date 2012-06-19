package core

object Config {
  final val awsAccessKey = System.getenv("AWS_ACCESS_KEY")
  final val awsSecretKey = System.getenv("AWS_SECRET_KEY")
  final val eccInstance = System.getenv("EC_INSTANCE")
  final val backgroundURL = System.getenv("BG_URL")
  final val fontName = System.getenv("FONT_NAME")
  final val appLabel = System.getenv("APP_LABEL")
}
