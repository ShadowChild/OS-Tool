package uk.co.innoxium.ostool.platform.win.cfg

import uk.co.innoxium.ostool.menu.MenuTool

class Hibernate : MenuTool("Disable Hibernate") {

    override fun runTool() {

        println("Disabling Hibernate!")
        val process = Runtime.getRuntime().exec("powercfg.exe /hibernate off")
        val handle = process.toHandle().onExit()
        handle.get()
        handle.thenAccept {

            println("Process completed.")
        }
    }
}
