package kapadokia.nyandoro.course.utils;

public class CalculateRange {

    public boolean isAboveHalf(String total, String paid){
        double totalAmt = Integer.parseInt(total);
        double paidAmt = Integer.parseInt(paid);

        double range = paidAmt/totalAmt;
        double percent = range * 100;
        if (percent<50){
            return false;
        }else {
            return true;
        }
    }
}
