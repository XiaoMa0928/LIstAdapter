package com.example.adapter_listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {

	 private ListView listView;   
	    private ImageButton imgbt_sum;   
	    private MyAdapter mMyAdapter;   
	    private List<Map<String, Object>> listItems;   
	    private Integer[] imgeIDs = {R.drawable.img1,    
	            R.drawable.img2, R.drawable.img3,   
	            R.drawable.img4, R.drawable.img5,   
	            R.drawable.img6};   
	    private String[] goodsNames = {"蛋糕", "礼物",    
	            "邮票", "爱心", "鼠标", "音乐CD"};   
	    private String[] goodsDetails = {   
	            "蛋糕：好好吃。",    
	            "礼物：礼轻情重。",    
	            "邮票：环游世界。",    
	            "爱心：世界都有爱。",   
	            "鼠标：反应敏捷。",   
	            "音乐CD：酷我音乐。"};   
	       
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {   
	        super.onCreate(savedInstanceState);   
	        setContentView(R.layout.main);   
	        listView = (ListView)findViewById(R.id.list_goods);    
	        imgbt_sum = (ImageButton) findViewById(R.id.imgbt_sum);   
	        imgbt_sum.setOnClickListener(new ClickEvent());   
	        listItems = getListItems();   
	        mMyAdapter = new MyAdapter(this, listItems); //创建适配器   
	        listView.setAdapter(mMyAdapter);   
	    }   
	       
	    /**  
	     * 初始化商品信息  
	     */  
	    private List<Map<String, Object>> getListItems() {   
	        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();   
	        for(int i = 0; i < goodsNames.length; i++) {   
	            Map<String, Object> map = new HashMap<String, Object>();    
	            map.put("image", imgeIDs[i]);               //图片资源   
	            map.put("title", "物品名称：");              //物品标题   
	            map.put("info", goodsNames[i]);     //物品名称   
	            map.put("detail", goodsDetails[i]);	//物品详情   
	            listItems.add(map);   
	        }      
	        return listItems;   
	    }   
	       
	    class ClickEvent implements OnClickListener{   
	  
	        @Override  
	        public void onClick(View v) {   
	            // TODO Auto-generated method stub   
	            String goodsList = "";   
	            for(int i = 0; i < listItems.size(); i++) {   
	                goodsList += mMyAdapter.mCheckedItem[i]? goodsNames[i] + "  ": "";   
	            }   
	            new AlertDialog.Builder(MainActivity.this)   
	            .setTitle("购物清单：")   
	            .setMessage("你好，你选择了如下商品：\n" + goodsList)   
	            .setPositiveButton("确定", null)   
	            .show();   
	        }   
	           
	    }   
	}   
