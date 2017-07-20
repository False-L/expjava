package HttpMain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by web on 2017/7/20.
 */
public class Get {
    static String sendGet(String url){
        //定义一个字符串用来存储网页
        String result="";
        BufferedReader in =null;
        try{
            //将string转成url对象
            URL realUrl=new URL(url);
            //初始化一个链接到那个url的连接
            URLConnection connection=realUrl.openConnection();
            //开始实际的连接
            connection.connect();
            //初始化bufferedReader输入流来读取url的响应
            in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //用来临时存储抓去到的每一行数据
            String line;
            while ((line=in.readLine())!=null)
            {
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送get请求出现异常！" +e);
            e.printStackTrace();
        }
        //
        finally
        {
            try{
                if((in !=null)){
                    in.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return result;
    }
    static String RegexString(String targetStr,String patternStr){
        //定义一个样式模版，此中使用正则表达式
        Pattern pattern=Pattern.compile(patternStr);
        Matcher matcher=pattern.matcher(targetStr);
        if(matcher.find()){
            //
            return matcher.group(1);
        }
        return "nothing";
    }
    public static void main(String[] args)
    {
        // 定义即将访问的链接
        String url = "http://www.baidu.com";
        // 访问链接并获取页面内容
        String result = sendGet(url);
        // 使用正则匹配图片的src内容
        String imgSrc = RegexString(result, "src=\"(.+?)\""); //
        // 打印结果
        System.out.println(result);
    }
}
