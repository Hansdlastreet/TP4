public class Filters implements IFilterRemote {

    @Override
    public void f1(Lecture l) {
        System.out.println("lecture cote serveur presence"+l.getValue()+" timestamp "+l.getTimestamp() );
    }

    @Override
    public void f2(Lecture l) {
        System.out.println("filter 2");
    }

    @Override
    public void noFilter(Lecture l) {
        System.out.println("filter 3");
    }
}

