package kr.ac.kpu.block.dcinside;


import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public TextView textView;
    public cHandler handler;
    public Button button;
    public Button menu;
    public ArrayList<String> list = new ArrayList<>();
    public String defs = "baseball_new7";
    String title = "";
    public class cHandler extends Handler{//메인 스레드 동작을 위한 커스텀 핸들러
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);





            for (int i = 5; i < list.size(); i++) {//
                if (!list.get(i).isEmpty()) {
                    if (i == 5) {
                        title = list.get(i);
                        System.out.println(i + " = " + list.get(i));
                    } else {
                        title = title + "\n" + list.get(i);
                        System.out.println(i + " = " + list.get(i));
                    }
                }
            }
            textView.setText(title);
            list.clear();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  /* 화면세로고정 */
        textView = (TextView) findViewById(R.id.tv);
        button = (Button) findViewById(R.id.btn);
        menu = (Button) findViewById(R.id.Menu);
        handler = new cHandler();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
                alertdialog.setTitle("이동할 갤러리의 끝 주소를 입력해주세요");
                final EditText et = new EditText(MainActivity.this);
                alertdialog.setView(et);
                alertdialog.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        defs = et.getText().toString();
                    }
                });
                alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = alertdialog.create();
                alert.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true) {


                                Document document =
                                        Jsoup.connect("http://gall.dcinside.com/board/lists/?id="+defs)
                                                .header("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                                                .header("Accept-Encoding", "gzip, deflate")
                                                .header("Accept-Language", "ko")
                                                .header("Connection", "Keep-Alive")
                                                .header("Cookie", "PHPSESSID=63d832fc6af1ed8bbb8878e342bc49bb; service_code=21ac6e96ad152e8f15a05b7350a24759b5606fa191c17e042e4d0175735e4761e403bede0ed282e9f9d52811d885f942f9d5e2206991db6c454d9f86da2d48ad569f17f2b2b6349791b9049899944d4d9af830ec24efb9daaa36b3dd18b77fc4ba86be4a41f41784dc8ce238e5ab7cee3bd65779c93a39a503186d3cc116c642aabecc1ee1ef62879524e6f90d0de5d01a3ae93fb9c393768f6657779dd177ec19a22204413c68; __utmc=118540316; alarm_new=1; __utmz=118540316.1538141241.85.73.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); ck_lately_gall=4yh%7CbP%7CId%7CzQ%7C1Qo; __gads=ID=1b20a094eb28cc5d:T=1531996729:S=ALNI_MYohlGfuLcnscbDHpxTPgYX9AWJCw; last_alarm=1538141750; __utma=118540316.403329469.1531957824.1538124353.1538141238.85; __utmb=118540316.43.10.1538141241; ci_session=a%3A5%3A%7Bs%3A10%3A%22session_id%22%3Bs%3A32%3A%2234a1d5958ad43b398ecbfa22e476d695%22%3Bs%3A10%3A%22ip_address%22%3Bs%3A13%3A%22112.150.250.6%22%3Bs%3A10%3A%22user_agent%22%3Bs%3A69%3A%22Mozilla%2F5.0+%28Windows+NT+10.0%3B+WOW64%3B+Trident%2F7.0%3B+rv%3A11.0%29+like+Gecko%22%3Bs%3A13%3A%22last_activity%22%3Bi%3A1538141541%3Bs%3A9%3A%22user_data%22%3Bs%3A0%3A%22%22%3B%7Dea5d15c562552f91969f629352f3eac8; _gid=GA1.2.1983273930.1538124353; gallRecom=MjAxOC0wOS0yOCAyMjozNDozMS84NGUyMTQzYTFlYjc3NzkwYTIxZmE4Zjc0ODQ2NDljOWQ3YzRkOGM0MjVmNDJiNWM0YmFkNDY4NGFhYWM1ODcx; _ga=GA1.2.403329469.1531957824; __utmt=1; lately_cookie=girlsfashion%7C%uC5EC%uC790%uD328%uC158%7C16@@2d_fightgame%7C2D%20%uACA9%uD22C%uAC8C%uC784%7C21@@sigong%7C%uC2DC%uACF5%7C12@@pebble%7C%uB3CC%7C12@@baseball_new6%7C%uC774%uC804%20%uAD6D%uB0B4%uC57C%uAD6C%7C20@@laptop%7C%uB178%uD2B8%uBD81%7C8@@bitcoins%7C%uBE44%uD2B8%uCF54%uC778%7C9@@baseball_new5%7C%uC774%uC774%uC804%20%uAD6D%uB0B4%uC57C%uAD6C%7C20@@pizza%7C%uD53C%uC790%7C7@@kpu%7C%uD55C%uAD6D%uC0B0%uC5C5%uAE30%uC220%uB300%7C33@@chicken%7C%uCE58%uD0A8%7C7@@revolution%7C%uD601%uBA85%7C13@@dota2%7C%uB3C4%uD0C0%202%7C19@@baseball_new4%7C%uC774%uC774%uC774%uC804%20%uAD6D%uB0B4%uC57C%uAD6C%7C20@@game_classic1%7C%uACE0%uC804%uAC8C%uC784%7C18@@midas%7C%uB9C8%uC774%uB354%uC2A4%7C21@@fantasy_new%7C%uD310%uD0C0%uC9C0%7C8@@monmusu%7C%uBAAC%uBB34%uC2A4%7C12@@programming%7C%uD504%uB85C%uADF8%uB798%uBC0D%7C11@@python%7C%uD30C%uC774%uC36C%7C13; ttx_t_r={\"1142850406\":{\"criteo\":\"0\"},\"1161683127\":{\"criteo\":\"0\"}}; ci_c=52f06b970d5312efe80074e26607821c; alarm_popup=1; GED_PLAYLIST_ACTIVITY=W3sidSI6InlHcVoiLCJ0c2wiOjE1MzgxNDEyNzAsIm52IjoxLCJ1cHQiOjE1MzgxNDEyNDksImx0IjoxNTM4MTQxMjY2fV0.; _mwuck=98d5d9c7-36dc-4ab5-b5c7-127e4d78c8e5; trc_cookie_storage=taboola%2520global%253Auser-id%3Df4403c61-0be3-4814-8287-af8a41695098-tuct2477bfb; wcs_bt=f92eaecbc22aac:1538141786; siteUniqId=STU_5b56fc01HYVdMpGf; last_notice_no=24077")
                                                .header("Host", "gall.dcinside.com")
                                                .header("Referer", "http://gall.dcinside.com/board/lists/?id=baseball_new7")
                                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                                                .get();//rss 데이터 저장.


                                Elements elements = document.select(".gall_list td[class=gall_tit ub-word] a").not(".icon_notice");//item tag의 내용물 저장.
                                for (Element element : elements) {//elements에서 각각의 element마다 동작 수행

                                    add(element.ownText());

                                    // add(element.getElementsByTag("href").iterator().next().text());
                                    //add 함수를 통해 list에 블로그 내용을 저장.
                                }
                                Message message = handler.obtainMessage();
                                handler.sendMessage(message);// 헨들러를 통해서 메인 스레드에 신호 전달.
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });
    }

    public void add(String string) {
        list.add(string);//리스트에 추가
    }

}
