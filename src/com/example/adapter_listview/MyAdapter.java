package com.example.adapter_listview;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context mContext; // 运行上下文
	private List<Map<String, Object>> mListItems; // 商品信息集合
	public boolean[] mCheckedItem; // 记录商品选中状态

	public MyAdapter(Context context, List<Map<String, Object>> listItems) {
		this.mContext = context;
		this.mListItems = listItems;
		mCheckedItem = new boolean[listItems.size()];
	}

	/**
	 * 显示物品详情
	 * 
	 * @param clickID
	 */
	private void showDetailInfo(int clickID) {

		new AlertDialog.Builder(mContext)
				.setTitle("物品详情：" + mListItems.get(clickID).get("info"))
				.setMessage(mListItems.get(clickID).get("detail").toString())
				.setPositiveButton("确定", null).show();
	}

	/**
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		final int selectID = position;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, parent, false);
		} else {
			v = convertView;
		}

		ImageView itemImage = (ImageView) v.findViewById(R.id.imageItem);
		TextView itemTitle = (TextView) v.findViewById(R.id.titleItem);
		TextView itemInfo = (TextView) v.findViewById(R.id.infoItem);
		Button detail = (Button) v.findViewById(R.id.detailItem);
		CheckBox isChecked = (CheckBox) v.findViewById(R.id.checkItem);

		Map<String, Object> map = mListItems.get(position);
		// 设置文字和图片
		itemImage.setBackgroundResource((Integer) map.get("image"));
		itemTitle.setText((String) map.get("title"));
		itemInfo.setText((String) map.get("info"));
		// 注册按钮点击时间
		detail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 显示物品详情
				showDetailInfo(selectID);
			}
		});
		// 注册多选框状态事件处理
		isChecked
				.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// 记录物品选中状态
						mCheckedItem[selectID] = !mCheckedItem[selectID];
					}
				});

		return v;
	}

	public int getCount() {
		return mListItems.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public boolean hasChecked(int checkedID) {
		return mCheckedItem[checkedID];
	}
}