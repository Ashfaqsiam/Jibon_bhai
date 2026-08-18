package bd.com.ashfaq.jibon_bhai;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    // Main Layouts - CHANGED TO 'View' TO PREVENT THE SCROLLVIEW CRASH
    private View scannerPage;
    private View loginPage;
    private View registerPage;
    private View loggedInContainer;

    // ViewPager
    private ViewPager2 viewPager;

    // Navigation TextViews
    private TextView navInbox, navGroups, navDash, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Edge-to-edge system bars
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Initialize Layouts
        scannerPage = findViewById(R.id.scannerPage);
        loginPage = findViewById(R.id.loginPage);
        registerPage = findViewById(R.id.registerPage);
        loggedInContainer = findViewById(R.id.loggedInContainer);

        // ==========================================
        // LOGIN & REGISTRATION LOGIC
        // ==========================================
        Button btnInitialize = findViewById(R.id.btnInitialize);
        Button btnGenerateId = findViewById(R.id.btnGenerateId);
        Button btnInitiateLogin = findViewById(R.id.btnInitiateLogin);
        Button btnReturnLogin = findViewById(R.id.btnReturnLogin);
        TextView tvAbort = findViewById(R.id.tvAbort);

        if (btnInitialize != null) btnInitialize.setOnClickListener(v -> switchPage(loginPage));
        if (tvAbort != null) tvAbort.setOnClickListener(v -> switchPage(scannerPage));
        if (btnGenerateId != null) btnGenerateId.setOnClickListener(v -> switchPage(registerPage));
        if (btnReturnLogin != null) btnReturnLogin.setOnClickListener(v -> switchPage(loginPage));

        if (btnInitiateLogin != null) {
            btnInitiateLogin.setOnClickListener(v -> {
                switchPage(loggedInContainer);
                if (viewPager != null) {
                    viewPager.setCurrentItem(0, false); // Default to Inbox without smooth scroll animation
                }
            });
        }

        // ==========================================
        // VIEWPAGER & FRAGMENT LOGIC
        // ==========================================
        viewPager = findViewById(R.id.viewPager);
        if (viewPager != null) {
            // This connects your ViewPagerAdapter class to the viewPager
            ViewPagerAdapter adapter = new ViewPagerAdapter(this);
            viewPager.setAdapter(adapter);

            // Swipe ViewPager -> Change Navigation Bar Color Automatically
            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    updateNavigationColors(position);
                }
            });
        }

        // Initialize Navigation Text Views
        navInbox = findViewById(R.id.navInbox);
        navGroups = findViewById(R.id.navGroups);
        navDash = findViewById(R.id.navDash);
        navProfile = findViewById(R.id.navProfile);
        TextView navLogout = findViewById(R.id.navLogout);

        // Click on Navigation Bar -> Change ViewPager Page smoothly
        if (navInbox != null) navInbox.setOnClickListener(v -> { if (viewPager != null) viewPager.setCurrentItem(0, true); });
        if (navGroups != null) navGroups.setOnClickListener(v -> { if (viewPager != null) viewPager.setCurrentItem(1, true); });
        if (navDash != null) navDash.setOnClickListener(v -> { if (viewPager != null) viewPager.setCurrentItem(2, true); });
        if (navProfile != null) navProfile.setOnClickListener(v -> { if (viewPager != null) viewPager.setCurrentItem(3, true); });

        if (navLogout != null) navLogout.setOnClickListener(v -> switchPage(loginPage));
    }

    // Controls the color and bold style of Bottom Navigation text
    private void updateNavigationColors(int position) {
        if (navInbox == null || navGroups == null || navDash == null || navProfile == null) return;

        // 1. Reset all to gray & normal font
        navInbox.setTextColor(Color.parseColor("#8F9BB3"));
        navGroups.setTextColor(Color.parseColor("#8F9BB3"));
        navDash.setTextColor(Color.parseColor("#8F9BB3"));
        navProfile.setTextColor(Color.parseColor("#8F9BB3"));

        navInbox.setTypeface(null, Typeface.NORMAL);
        navGroups.setTypeface(null, Typeface.NORMAL);
        navDash.setTypeface(null, Typeface.NORMAL);
        navProfile.setTypeface(null, Typeface.NORMAL);

        // 2. Set active tab to Blue & Bold based on swipe position
        if (position == 0) {
            navInbox.setTextColor(Color.parseColor("#3975C6"));
            navInbox.setTypeface(null, Typeface.BOLD);
        } else if (position == 1) {
            navGroups.setTextColor(Color.parseColor("#3975C6"));
            navGroups.setTypeface(null, Typeface.BOLD);
        } else if (position == 2) {
            navDash.setTextColor(Color.parseColor("#3975C6"));
            navDash.setTypeface(null, Typeface.BOLD);
        } else if (position == 3) {
            navProfile.setTextColor(Color.parseColor("#3975C6"));
            navProfile.setTypeface(null, Typeface.BOLD);
        }
    }

    // Switches between Auth Pages and Logged In Area
    private void switchPage(View pageToShow) {
        if(scannerPage != null) scannerPage.setVisibility(View.GONE);
        if(loginPage != null) loginPage.setVisibility(View.GONE);
        if(registerPage != null) registerPage.setVisibility(View.GONE);
        if(loggedInContainer != null) loggedInContainer.setVisibility(View.GONE);

        if(pageToShow != null) pageToShow.setVisibility(View.VISIBLE);
    }
}