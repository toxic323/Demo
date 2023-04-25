import files.payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class complexJsonDummy {

    //sprawdz czy totalAmount(priceAmount), zgadza sie z poszczegolnymi w courses
    @Test
    public void sumVerification() {
        JsonPath jsResponse = new JsonPath(payloads.coursePrice());
        int priceAmount = jsResponse.getInt("dashboard.purchaseAmount");
        int coursesAmount = jsResponse.getInt("courses.size()");

        int sum = 0;
        for (int i = 0; i < coursesAmount; i++) {
            int temp = jsResponse.getInt("courses[" + i + "].copies") * jsResponse.getInt("courses[" + i + "].price");
            sum = sum + temp;
        }
        System.out.println("Suma wyliczona to: " + sum);
        System.out.println("Suma z API to: " + priceAmount);
        Assert.assertEquals(sum, priceAmount);
    }

/*
    public static void main(String[] args){
        JsonPath jsResponse = new JsonPath(payloads.coursePrice());
        int coursesAmount = jsResponse.getInt("courses.size()");
        int priceAmount = jsResponse.getInt("dashboard.purchaseAmount");
        String secondTitle = jsResponse.getString("courses[1].title");
        String titles = jsResponse.getString("courses.title");
        String root = jsResponse.getString("$");

        System.out.println(coursesAmount);
        System.out.println(priceAmount);
        System.out.println(secondTitle);
	System.out.println("Dodatkowy z 1usera");
        System.out.println(titles);
        System.out.println(root);


        //liczba kopi dla kursu RPA, jak znajdziesz to wyjdz z loop
        for(int i=0;i<coursesAmount;i++){
            String title =jsResponse.getString("courses["+i+"].title");
            if(title.equalsIgnoreCase("rpa")){
                System.out.println("liczba kopii dla kursu RPA:" + jsResponse.getString("courses["+i+"].copies"));
                break;
            }
        }
    }
    */
}
