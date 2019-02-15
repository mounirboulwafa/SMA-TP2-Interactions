package agents;

import containers.BuyerContainer;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class Buyer extends GuiAgent {
    private BuyerContainer gui;

    @Override
    protected void setup() {
        /* get BuyerContainer Object */
        gui = (BuyerContainer) getArguments()[0];
        gui.setBuyerAgent(this);

        System.out.println("Agent " + this.getAID().getLocalName() + " is starting ...");

        /* Message 1 : send a message to agent : Seller */
//        addBehaviour(new OneShotBehaviour() {
//            @Override
//            public void action() {
//                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
//                message.addReceiver(new AID("Seller", AID.ISLOCALNAME));
//                message.setContent("Je chercher un live a acheter !!");
//                send(message);
//            }
//        });
//
//        /* receive the message */
//        addBehaviour(new CyclicBehaviour() {
//            @Override
//            public void action() {
//                ACLMessage msg = myAgent.receive();
//                if (msg != null) {
////                    System.out.println(msg.getSender().getLocalName() + " : " + msg.getContent());
//                    GuiEvent guiEvent = new GuiEvent(this, 1);
//                    guiEvent.addParameter(msg);
//                    gui.viewMessage(guiEvent);
//                }
////                else
////                    block();
////                    System.out.println("dddddddddddddddddddddd");
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
        System.out.println("Agent " + this.getAID().getLocalName() + " distraction ...");
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        if (guiEvent.getType() == 1) {
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
            message.addReceiver(new AID("Seller", AID.ISLOCALNAME));

            String livre = guiEvent.getParameter(0).toString();
            message.setContent(livre);
            message.addReceiver(new AID("rma", AID.ISLOCALNAME));
            send(message);
        }
    }
}
