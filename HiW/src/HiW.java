import java.util.Scanner;

public class HiW {
        public static void main(String[] args){
            boolean first_calc = true;
            double T_dry = 0.0, T_wet = 0.0;
            double Arh = 0.0, Rh = 0.0;
            double VelocityRH = 0.0;
            Scanner in = new Scanner(System.in);;
//            System.out.println("-----------------------------------");
            System.out.println( "Relative humidity calculation \n" +
                                "*******************************\n");
            System.out.println("Hello! Let's calc Rh");
            while(true) {
                if (first_calc == false) System.out.println("One more calc?!?!?!");
                else
                {
                    System.out.println("Velocity:");
                    try{
                        VelocityRH = in.nextFloat();
                    } catch (java.util.InputMismatchException e)  {System.out.println("NO dots, use \",\"!"); in.next(); continue;}
                }
                first_calc = false;
                System.out.println("T dry:");
//                float T_dry = in.nextFloat();
                try{
                    T_dry = in.nextFloat();
                } catch (java.util.InputMismatchException e)  {System.out.println("NO dots, use \",\"!"); in.next(); continue;}

                System.out.println("T wet:");
//                float T_wet = in.nextFloat();
                try{
                    T_wet = in.nextFloat();
                } catch (java.util.InputMismatchException e)  {System.out.println("NO dots, use \",\"!"); in.next(); continue;}

                //calculation
                if ( (T_dry > T_wet) & (T_dry > 0))
                {
                    Arh = CalcArh(VelocityRH);
                    Rh = ((CalcE(T_wet)-Arh*(T_dry - T_wet))/CalcE(T_dry)*100.0+ 0)*1;
                }
                else System.out.println("Wrong values!");

                if ( Rh <= 0 ) Rh = 0; //RH_Status[irh] = 1;
                if ( Rh > 100 ) Rh = 100;
                System.out.format("Calculated Rh: %3.2f%%\n\n", Rh);
            }
        }
        private static double CalcArh(double fV)
        {double flTMP;
         double AA = 0.000515, BA = 0.00047, CA = -0.00007;
            flTMP = (AA + BA/fV + CA/fV/fV)*1000.0;
            return flTMP;
        }
        private static double CalcE(double fT)
        {
            double fTMP, fTMP1;
            double CE4 = 3.0e-6,CE3 = 2.48e-4, CE2 = 1.0104e-2, CE1 = 1.02615e-1, CE0 = 4.84937e-1;

            fTMP = fT+46.0;

            fTMP1 = CE4*fTMP*fTMP*fTMP*fTMP;
            fTMP1 = fTMP1 - CE3*fTMP*fTMP*fTMP;
            fTMP1 = fTMP1 + CE2*fTMP*fTMP;
            fTMP1 = fTMP1 - CE1*fTMP;
            fTMP1 = fTMP1 + CE0;
            return fTMP1;
        }
}
