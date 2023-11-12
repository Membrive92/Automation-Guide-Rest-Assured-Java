package com.rest.Utils;

import java.util.Base64;

public class Utils {
    public static String postmanApiKey(){
        String encoderString = "UE1BSy02NGNlMjJiNDc1Nzk2MTc0Y2Q5M2RmYmEtZDVmOWI0ZDFmNmFmYmY2M2JhOGYzMjQxM2U3OWU3MTc2YQ==";
        byte[] bytesDecodes = Base64.getDecoder().decode(encoderString);

        return new String(bytesDecodes);
    }

    public static void base64Example(){
        String text64 = "username:password";

        String base64Encoded = Base64.getEncoder().encodeToString(text64.getBytes());
        System.out.println("Encoded = " + base64Encoded);
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        System.out.println("Decoded = " + new String(decodedBytes));
    }
    //271848153370-bv90b52h2gflqadj3jasl2lefrtnjb66.apps.googleusercontent.com
    //"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"
    // authcode   4%2F0AfJohXkygZ1MdYfkCGCp-H8Fx0u1HnYOnk7dvhv3cp5JxOKgHVp-p5T_hwwBSJ2XrfBxag
    /* {
    "access_token": "ya29.a0AfB_byA9Sd1dpyq8S8roNRvMZPg2pc8w2dMHqD6KeCMmqMKlC0BA2Olk80KRCe313WabxKOXhzXK6ulWNreNjGDuHmeZAMWzlFyM5f5Uljs7DISvSnRsowTsnDFbLVDbAXoN4yLA0dk7wjrHUkDpKvVUdt5UxFqYdpsHaCgYKAYcSARESFQHGX2Mi5Uvpwtbk0HgZOwjVqEzC_w0171",
    "expires_in": 3599,
    "refresh_token": "1//03wsGmuvBrfTACgYIARAAGAMSNwF-L9IrjnhhvQMIQvGjYl_A16xurKM-Ahm7LbpgluZlXOCIEE5IlIV0kiPsK6GF8YdowhpOLFw",
    "scope": "https://mail.google.com/",
    "token_type": "Bearer"
    Implicittokenflow
    ya29.a0AfB_byBLfJyPai5K7cKmKYhMpNwt6_C0QtxyvU_I01etSCYPHZCpOA2lzx_XiLqmwjduzWExMoCPJ4upCxKaCjA9uKksuzlHMKK_-thWdxxsvr8cZPf1BovrlmGuxybzBlaIby7NUB2axwcIqbmmbmSzk-e0yjrK-AaCgYKAWcSARESFQHGX2Mi1qfJZK2FOPsmwk6VXNSOZw0169
} */
}
