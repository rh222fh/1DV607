import Back.Boat;
import Back.Member;
import Back.Registry;

public class main {

    public static void main(String[] args) {

        Registry reg = new Registry();
        Member mem =  new Member("890705-2990", "Rilind");
        Member mem2 =  new Member("890705-2990", "Alexander");
        Member mem3 =  new Member("890705-2990", "Sandra");

        //mem.addBoat(boat);
        reg.addMember(mem);
        reg.addMember(mem2);
        reg.addMember(mem3);

        Boat boat = new Boat(Boat.Type.Canoe, 10);
        reg.getMember(1).addBoat(boat);

        mem.addBoat(boat);
        reg.getCompactList();
        System.out.println();
        reg.getVerboseList();


    }
}
