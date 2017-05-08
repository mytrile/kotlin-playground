package com.kotlinplayground

import akka.actor.AbstractActor

class Greeter : AbstractActor() {

    enum class Msg {
        GREET, DONE
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .matchEquals(Msg.GREET) { m ->
                    println("Hello World!")
                    sender().tell(Msg.DONE, self())
                }
                .build()
    }
}

