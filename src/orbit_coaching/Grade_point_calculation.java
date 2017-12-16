package orbit_coaching;

public  class Grade_point_calculation {

    public static String get_grade(String m)
    {
        int marks = Integer.parseInt(m);
//        80-100	A+	5
//        70-79	A	4
//        60-69	A-	3.5
//        50-59	B	3
//        40-49	C	2
//        33-39	D	1
//        0-32	F	0

        if(marks>=80 && marks<=100)
            return "A+";
        else if(marks>=70 && marks<=79)
            return "A";
        else if(marks>=60 && marks<=69)
            return "A-";
        else if(marks>=50 && marks<=59)
            return "B";
        else if(marks>=40 && marks<=49)
            return "C";
        else if(marks>=33 && marks<=39)
            return "D";
        else return "F";
    }
    public static double get_grade_point(String m)
    {
       int marks = Integer.parseInt(m);
        //        80-100	A+	5
//        70-79	A	4
//        60-69	A-	3.5
//        50-59	B	3
//        40-49	C	2
//        33-39	D	1
//        0-32	F	0

        if(marks>=80 && marks<=100)
            return 5.00;
        else if(marks>=70 && marks<=79)
            return 4.00;
        else if(marks>=60 && marks<=69)
            return 3.50;
        else if(marks>=50 && marks<=59)
            return 3.00;
        else if(marks>=40 && marks<=49)
            return 2.00;
        else if(marks>=33 && marks<=39)
            return 1.00;
        else return 0.00;
    }
    static String get_grade_from_point(double point)
    {
        if(point+.0001>=5.00)
            return "A+";
        else if(point+.0001>=4.00)
            return "A";
        else if(point+.0001>=3.50)
            return "A-";
        else if(point+.0001>=3.00)
            return "B";
        else if(point+.0001>=2.00)
            return "C";
        else if(point+.0001>=1.00)
            return "D";
        else return "F";
    }
}
