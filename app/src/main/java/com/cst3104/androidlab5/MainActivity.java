package com.cst3104.androidlab5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ChatAdapter adapter;// Adapter for the chat messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the main layout

        // Initialize UI elements
        Button sendButton = findViewById(R.id.sendButton); // Button to send messages
        Button receiveButton = findViewById(R.id.receiveButton); // Button to receive messages
        final EditText messageEditText = findViewById(R.id.messageEditText); // Text input field for messages

        // Initialize the chat message adapter
        adapter = new ChatAdapter(this, new ArrayList<Message>());

        // Initialize the ListView to display chat messages and set the adapter
        ListView messageListView = findViewById(R.id.messageListView);
        messageListView.setAdapter(adapter);

        // Define an action for the sendButton click event
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString();
                Message message = new Message(messageText, true); // Create a sent message
                adapter.addMessage(message); // Add the message to the chat
                messageEditText.setText(""); // Clear the input field
                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
            }
        });

        // Define an action for the receiveButton click event
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString();
                Message message = new Message(messageText, false); // Create a received message
                adapter.addMessage(message); // Add the message to the chat
                messageEditText.setText(""); // Clear the input field
                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
            }
        });

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Message messageClicked = (Message) adapter.getItem(position);

                // Create a dialog to confirm message deletion
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert")
                        .setMessage("Do you want to delete this message?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the "Yes" button click - delete the message
                                adapter.deleteMessage(messageClicked);
                                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
                                Toast.makeText(MainActivity.this, "Message deleted", Toast.LENGTH_SHORT).show(); // Display a message
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the "No" button click - dismiss the dialog
                                dialog.dismiss(); // Dismiss the dialog
                                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show(); // Display a message
                            }
                        });

                // Create and show the alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}