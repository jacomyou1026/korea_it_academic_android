package com.libra.vaccine.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.libra.vaccine.CustomAdapters.GridListAdapter;
import com.libra.vaccine.VO.RegionConfirmedList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Covid19Task extends AsyncTask<String, String, ArrayList<RegionConfirmedList>> {
    String regionName = null;
    String deathCnt = null;
    String incDec = null;
    String defCnt = null;
    Date currentTime = Calendar.getInstance().getTime();
    GridListAdapter adapter;

    //20220311
    SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
    String currents = sdfs.format(currentTime);


    public Covid19Task(GridListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<RegionConfirmedList> doInBackground(String... strings) {

        ArrayList<RegionConfirmedList> result = new ArrayList<>();
        String results = null;
        try {
            StringBuilder urlBuilder = new StringBuilder(
                    "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /* URL */
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + "=PJgXs4FpAyoSQm3FnFxDCtIg1thkVBDIrn6DqPM38DFpD43CMzE%2BPisiMxns%2FZ6X2AbN%2FL0e0PZYrl0JVokqTA%3D%3D"); /*
             * Service
             * Key
             */
            urlBuilder
                    .append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                    + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
            urlBuilder.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "="
                    + URLEncoder.encode(currents, "UTF-8")); /* 검색할 생성일 범위의 시작 */
            urlBuilder.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "="
                    + URLEncoder.encode(currents, "UTF-8")); /* 검색할 생성일 범위의 종료 */

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(urlBuilder.toString());



            ArrayList<String> arrayList = new ArrayList<>();
            NodeList tagList = document.getElementsByTagName("item");
            for (int i = 0; i < tagList.getLength(); i++) {
                NodeList list = tagList.item(i).getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    switch (list.item(j).getNodeName()) {
                        //지역명
                        case "gubun":
                            this.regionName =  list.item(j).getTextContent();
                            break;

                        //확진자 수
                        case "defCnt":
                            this.defCnt = list.item(j).getTextContent();
                            break;

                        //전일대비 증감 수
                        case "incDec":
                            this.incDec = list.item(j).getTextContent();
                            break;


                        //사망자 수
                        case "deathCnt":
                            this.deathCnt = list.item(j).getTextContent();
                            break;

                        //  Log.d("covid", list.item(j).getNodeName() + " - " + list.item(j).getTextContent()); // 태그명 - 내

                    }
                    //   Log.d("covid1", String.valueOf(result)); // 태그명 - 내용
                }

                result.add(new RegionConfirmedList(regionName,defCnt,incDec,deathCnt));
                Log.d("covid",result.get(0).getReg_defCnt() );
                Log.d("covid",regionName);
                Log.d("covid", "----------------------------");
            }

        } catch (UnsupportedEncodingException | ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<RegionConfirmedList> s) {
        super.onPostExecute(s);

        adapter.clear();
        for (int i = s.size()-2; i >+ 0; i--) {
            //            adapter.addItem(s.get(i));
            Log.d("get",s.get(i).getReg_incDec()+"-전일");
            Log.d("get",s.get(i).getReg_defCnt()+"-def");
            Log.d("get",s.get(i).getReg_death()+"-사망");
            Log.d("get",s.get(i).getRegionName()+"-지역");
            Log.d("get","---------------------");

            RegionConfirmedList regionConfirmedList = new RegionConfirmedList();

            regionConfirmedList.setRegionName(s.get(i).getRegionName());

            regionConfirmedList.setReg_death(s.get(i).getReg_death());

            regionConfirmedList.setReg_incDec(s.get(i).getReg_incDec());

            regionConfirmedList.setReg_defCnt(s.get(i).getReg_defCnt());

            Log.d("gets", String.valueOf(regionConfirmedList));
            adapter.addItem(regionConfirmedList);
        }
        adapter.notifyDataSetChanged();
        s.clear();

    }
}