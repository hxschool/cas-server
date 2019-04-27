package org.jasig.cas.util;

public class CasUtility
{
  public static String resetUrl(String casUrl)
  {
    String[] paramsToBeRemoved = { "lt", "error_message", "get-lt" };
    String cleanedUrl = removeHttpGetParameters(casUrl, paramsToBeRemoved);
    return cleanedUrl;
  }
  
  public static String removeHttpGetParameters(String casUrl, String[] paramsToBeRemoved)
  {
    String cleanedUrl = casUrl;
    if (casUrl != null)
    {
      if (casUrl.indexOf("?") == -1) {
        return casUrl;
      }
      boolean containsOneOfTheUnwantedParams = false;
      String[] arrayOfString;
      int j = (arrayOfString = paramsToBeRemoved).length;
      for (int i = 0; i < j; i++)
      {
        String paramToBeErased = arrayOfString[i];
        int startPosition = -1;
        int endPosition = -1;
        if (cleanedUrl.indexOf("?" + paramToBeErased + "=") > -1) {
          startPosition = cleanedUrl.indexOf("?" + 
            paramToBeErased + "=") + 1;
        } else if (cleanedUrl.indexOf("&" + paramToBeErased + "=") > -1) {
          startPosition = cleanedUrl.indexOf("&" + 
            paramToBeErased + "=") + 1;
        }
        if (startPosition > -1)
        {
          int temp = cleanedUrl.indexOf("&", startPosition);
          endPosition = temp > -1 ? temp + 1 : cleanedUrl
            .length();
          
          cleanedUrl = cleanedUrl.substring(0, startPosition) + 
            cleanedUrl.substring(endPosition);
          containsOneOfTheUnwantedParams = true;
        }
      }
      if ((cleanedUrl.endsWith("?")) || (cleanedUrl.endsWith("&"))) {
        cleanedUrl = cleanedUrl.substring(0, 
          cleanedUrl.length() - 1);
      }
      if (!containsOneOfTheUnwantedParams) {
        return casUrl;
      }
      cleanedUrl = removeHttpGetParameters(cleanedUrl, 
        paramsToBeRemoved);
    }
    return cleanedUrl;
  }
}
