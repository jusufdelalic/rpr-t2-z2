package ba.unsa.etf.rpr.tutorijal02;

public class Interval {

    double pocetnaTacka;
    double krajnjaTacka;
    boolean pripadaPoc;
    boolean pripadaKraj;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pripadaPocetnaTacka, boolean pripadaKrajnjaTacka) {

        if(pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException();
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pripadaPoc = pripadaPocetnaTacka;
        this.pripadaKraj = pripadaKrajnjaTacka;
    }

    public Interval() {
        pocetnaTacka = krajnjaTacka = 0;
        pripadaPoc = pripadaKraj = false;
    }

    public boolean isNull() { // mozda return this != null
        return pocetnaTacka == 0 && krajnjaTacka == 0 && pripadaPoc == false && pripadaKraj == false;
    }

    public boolean isIn(double tacka) {

        if( (pripadaPoc == true && tacka == pocetnaTacka) || (pripadaKraj == true && tacka == krajnjaTacka) ) return true;
        else if (tacka < krajnjaTacka && tacka > pocetnaTacka) return true;

        return false;
    }

    public Interval intersect (Interval interval) {
        if( this.krajnjaTacka < interval.pocetnaTacka || interval.krajnjaTacka <  this.pocetnaTacka ||
                ( this.krajnjaTacka == interval.pocetnaTacka &&
                (this.pripadaKraj != interval.pripadaKraj || this.pripadaKraj == interval.pripadaPoc == false) ) ||
                ( this.pocetnaTacka == interval.krajnjaTacka &&
                        (this.pripadaPoc != interval.pripadaKraj || this.pripadaPoc == interval.pripadaKraj == false) )

        ) return new Interval();

        else if(this.krajnjaTacka == interval.pocetnaTacka) return new Interval(interval.pocetnaTacka,this.krajnjaTacka,true,true);
        else if(this.pocetnaTacka == interval.krajnjaTacka) return new Interval(this.pocetnaTacka,interval.krajnjaTacka,true,true);

        else if(this.isIn(interval.pocetnaTacka) && this.isIn(interval.krajnjaTacka)) return new Interval(interval.pocetnaTacka,interval.krajnjaTacka,interval.pripadaPoc,interval.pripadaKraj);
        else if(interval.isIn(this.pocetnaTacka) && interval.isIn(this.krajnjaTacka)) return new Interval(this.pocetnaTacka,this.krajnjaTacka,this.pripadaPoc,this.pripadaKraj);

        else if(interval.pocetnaTacka < this.krajnjaTacka) return new Interval(interval.pocetnaTacka,this.krajnjaTacka,interval.pripadaPoc,this.pripadaKraj);
        else if(this.pocetnaTacka < interval.krajnjaTacka) return new Interval(this.pocetnaTacka,interval.krajnjaTacka,this.pripadaPoc,interval.pripadaKraj);

        return new Interval();
    }

    public static Interval intersect (Interval interval_1, Interval interval_2) {
        if(interval_1.isNull() || interval_2.isNull() || interval_1 == null || interval_2 == null) return new Interval();
        return interval_1.intersect(interval_2);
    }

    @Override
    public String toString() {

        if(this.isNull() || this == null) return new String("()");

        String pocetnaZagrada = "(";
        String krajnjaZagrada = ")";

        if(this.pripadaPoc == true && this.pripadaKraj == true) {
            pocetnaZagrada = "[";
            krajnjaZagrada = "]";
        }

        else if(this.pripadaPoc == true) pocetnaZagrada = "[";
        else if (this.pripadaKraj == true) krajnjaZagrada = "]";

        return new String(pocetnaZagrada + this.pocetnaTacka + "," + this.krajnjaTacka + krajnjaZagrada);


    }

    @Override
    public boolean equals(Object interval) {
        Interval i = (Interval) interval;
        return this.pocetnaTacka == i.pocetnaTacka && this.krajnjaTacka == i.krajnjaTacka &&
                this.pripadaKraj == i.pripadaKraj && this.pripadaPoc == i.pripadaPoc;
    }



}
