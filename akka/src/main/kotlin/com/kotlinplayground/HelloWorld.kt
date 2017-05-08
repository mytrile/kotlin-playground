package com.kotlinplayground

import akka.actor.AbstractActor
import akka.actor.Props

import com.kotlinplayground.Greeter.Msg

class HelloWorld : AbstractActor() {

    override fun createReceive(): Receive {
        return receiveBuilder()
                .matchEquals(Msg.DONE, { m -> getContext().stop(self()) })
                .build()
    }

    override fun preStart() {
        val greeter = getContext().actorOf(Props.create(Greeter::class.java), "greeter")
        greeter.tell(Msg.GREET, self())
    }
}
