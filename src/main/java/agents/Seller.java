package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Seller extends Agent {
    @Override
    protected void setup() {
        System.out.println("Agent " + this.getAID().getLocalName() + " is starting ...");

        /* receive the message sent from agent : Buyer */
//        addBehaviour(new CyclicBehaviour() {
//            @Override
//            public void action() {
//                ACLMessage msg = myAgent.receive();
//                if (msg != null) {
//                    System.out.println(msg.getSender().getLocalName() + " : " + msg.getContent());
//
//                    /* Message 2 : send the respond */
//                    ACLMessage respond = new ACLMessage(ACLMessage.INFORM);
//                    respond.addReceiver(msg.getSender());
//                    respond.setContent("Oui avec plaisir !");
//                    send(respond);
//
//                }
////                else
////                    block();
//            }
//        });
    }

    @Override
    protected void beforeMove() {
        System.out.println("Before Migration ...");
        System.out.println("Container : " + this.getContainerController().getName());
    }

    @Override
    protected void afterMove() {
        System.out.println("After Migration ...");
        System.out.println("Container : " + this.getContainerController().getName());

    }

    @Override
    protected void takeDown() {
        System.out.println("Agent distraction ...");
    }
}
