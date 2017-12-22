package orbit_coaching;

public class Data_validation {

    public static boolean check_date(String date)
    {
        try {
            if (date.length() != 10)
                return true;
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3,5));
            int year =Integer.parseInt(date.substring(6,10));
            System.out.println(date.length()+" "+day+" "+month+ " "+year);
            if(day<1 || day>31)
                return true;
            else if(month<1 || month>12)
                return true;
            else if(year<1950 || year>2117)
                return true;
            return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return true;
        }
    }
}
