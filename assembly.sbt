import sbtassembly.Plugin.AssemblyKeys
import AssemblyKeys._

assemblySettings

outputPath in assembly := new File("target/cer-rating-task-0.0.1-SNAPSHOT-jar-with-dependencies.jar")
