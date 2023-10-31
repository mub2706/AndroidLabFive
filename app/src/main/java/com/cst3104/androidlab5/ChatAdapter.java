package com.cst3104.androidlab5;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private ArrayList<Message> messageList;
    private LayoutInflater inflater;

    public ChatAdapter(Context context, ArrayList<Message> messages) {
        this.messageList = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_layout, null);
            holder = new ViewHolder();
            holder.sentMessageTextView = convertView.findViewById(R.id.sentMessageTextView);
            holder.receivedMessageTextView = convertView.findViewById(R.id.receivedMessageTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the message at the current position
        Message message = (Message) getItem(position);

        // Set the text in the appropriate TextView based on whether it's a sent or received message
        if (message.isSent()) {
            holder.sentMessageTextView.setText(" " + message.getText());
            holder.sentMessageTextView.setVisibility(View.VISIBLE);
            holder.receivedMessageTextView.setVisibility(View.GONE);
        } else {
            holder.receivedMessageTextView.setText(" " + message.getText());
            holder.sentMessageTextView.setVisibility(View.GONE);
            holder.receivedMessageTextView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public void deleteMessage(Message message) {
        messageList.remove(message);
    }

    static class ViewHolder {
        TextView sentMessageTextView;
        TextView receivedMessageTextView;
    }
}
