package com.anonymous.roomchat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class Msglist extends ArrayAdapter<Web_msg> {

    private Activity context;
    private List<Web_msg> messagelist;

    public Msglist(Activity context, List<Web_msg> messagelist){
        super(context, R.layout.list_layout, messagelist);
        this.context = context;
        this.messagelist = messagelist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textviewname = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textviewmessage = (TextView) listViewItem.findViewById(R.id.textViewMessage);

        Web_msg web_msg = messagelist.get(position);

        textviewname.setText(web_msg.getWeb_message_name());
        textviewmessage.setText(web_msg.getWeb_message());

        return listViewItem;
    }
}
