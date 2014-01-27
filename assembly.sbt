import sbtassembly.Plugin.AssemblyKeys
import AssemblyKeys._

assemblySettings

outputPath in assembly := new File("target/cer-rating-task.jar")
