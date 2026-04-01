package com.example.myapplication;
import android.os.Bundle;
        import android.widget.TextView;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;
public class UserInformation extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private TextView Name,Phone,Email;
    private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        Name = findViewById(R.id.NameOfuser);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.EmailOfuser);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UID = firebaseAuth.getCurrentUser().getUid();
        documentReference = firebaseFirestore.collection("users").document(UID);
        documentReference.addSnapshotListener((value, error) -> {
            Name.setText("Hello " + value.getString("FirstName") + " " +value.getString("LastName")+" !");
            Phone.setText("Your Number is '" + value.getString("Phone")+"'");
            Email.setText("And your Email '" + value.getString("Email")+"'");
        });
    }
}