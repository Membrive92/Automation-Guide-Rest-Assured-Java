package com.rest.Utils;

import java.util.Base64;

public class Utils {
    public static String postmanApiKey(){
        String encoderString = "UE1BSy02NGNlMjJiNDc1Nzk2MTc0Y2Q5M2RmYmEtZDVmOWI0ZDFmNmFmYmY2M2JhOGYzMjQxM2U3OWU3MTc2YQ==";
        byte[] bytesDecodes = Base64.getDecoder().decode(encoderString);

        return new String(bytesDecodes);
    }
}
