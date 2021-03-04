package uk.co.innoxium.ostool.platform.win.cfg

import uk.co.innoxium.ostool.OSTool
import uk.co.innoxium.ostool.menu.MenuTool

class Hibernate : MenuTool("Disable Hibernate") {

    override fun runTool() {

        println("Disabling Hibernate!")

        var ret = OSTool.rootExecutor.run {

            val processBuilder = ProcessBuilder("powercfg.exe","/hibernate off")
            val process = processBuilder.inheritIO().start()
//            val process = Runtime.getRuntime().exec("powercfg.exe /hibernate off")
            val handle = process.toHandle().onExit()
            handle.get()
            handle.thenAccept {

                println("Process completed.")
            }
        }
    }
}
