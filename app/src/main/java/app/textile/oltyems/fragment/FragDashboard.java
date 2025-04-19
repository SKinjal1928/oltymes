package app.textile.oltyems.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.ScannedBarcodeActivity;
import app.textile.oltyems.activity.SalesActivity;
import app.textile.oltyems.activity.ViewAllActivity;
import app.textile.oltyems.common.SharedPref;

import app.textile.oltyems.databinding.FragmentDashboardBinding;
import app.textile.oltyems.databinding.TabItemBinding;
import app.textile.oltyems.model.FetchShipmentList;
import app.textile.oltyems.retrofit.RetroInterface;

public class FragDashboard extends Fragment {

    TabItemBinding binding;
    RetroInterface apiInterface;
    List<FetchShipmentList.Datum> shipmentList;
    String Ship_id = "";
    ColorStateList def;
    LinearLayout selectedTab = null; // Keep track of selected tab
    List<LinearLayout> allTabs = new ArrayList<>();
    List<TextView> allTextViews = new ArrayList<>();
    List<ImageView> allIconViews = new ArrayList<>();
    private String[] tabNames = {"Inbox", "Transactions", "Updates"};
    private int[] tabIcons = {R.drawable.clock, R.drawable.process, R.drawable.check};
    private int selectedTabIndex = -1;

    public FragDashboard() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.frag_dashboard, container, false);
        binding = TabItemBinding.inflate(inflater, container, false);
        allTabs = Arrays.asList(binding.tabInbox, binding.tabProcess, binding.tabComplete);
        allTextViews = Arrays.asList(binding.textInbox, binding.textFinance, binding.textInvoice);
        allIconViews = Arrays.asList(binding.iconInbox, binding.iconFinance, binding.iconInvoice);
     /*   LinearLayout tabInbox = findViewById(R.id.tab_inbox);
        LinearLayout tabPromotions = findViewById(R.id.tab_promotions);
        LinearLayout tabUpdates = findViewById(R.id.tab_updates);*/

//        binding.bottomNavMenu.setupWithNavController(navController);

        // Default tab
        selectTab(binding.tabInbox1, binding.textInbox1);

        binding.tabInbox1.setOnClickListener(v -> {
            binding.viewFlipper.setDisplayedChild(0);
            selectTab(binding.tabInbox1, binding.textInbox1);
            deselectTab(binding.tabPromo, binding.textPromo);
        });

        binding.tabProcess.setOnClickListener(v -> {
            binding.viewFlipper.setDisplayedChild(1);
            selectTab(binding.tabPromo, binding.textPromo);
            deselectTab(binding.tabInbox1, binding.textInbox1);
        });


        // Set click listeners
        for (LinearLayout tab : allTabs) {
            tab.setOnClickListener(tabClickListener);
        }

        // Optionally select first tab by default
        tabClickListener.onClick(binding.tabInbox);
//        loadFragment(new AccountFragment()); // Set default
      /*  binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                loadFragment(new AccountFragment());
                return true;
            } else if (itemId == R.id.nav_search) {
                loadFragment(new AccountFragment());
                return true;
            } else if (itemId == R.id.nav_profile) {
                loadFragment(new AccountFragment());
                return true;
            }
            return false;
        });*/



    /*    binding.animatedTabLayout.addTab("Inbox", R.drawable.ic_inbox);
        binding.animatedTabLayout.addTab("Promos", R.drawable.ic_promo);
        binding.animatedTabLayout.addTab("Updates", R.drawable.ic_update);

// Optional: Set click listener
        binding.animatedTabLayout.setOnTabClickListener(new AnimatedTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                // Handle tab click
                Toast.makeText(getActivity(), "Tab " + position + " clicked", Toast.LENGTH_SHORT).show();

                // TODO: Smoothly update content below here
            }
        });
*/
        return binding.getRoot();
    }

    private void switchContent(int index) {
        Fragment fragment = null;

        switch (index) {
            case 0: fragment = new AccountFragment(); break;
            case 1: fragment = new AccountFragment(); break;
            case 2: fragment = new AccountFragment(); break;
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.contentFrame, fragment)
                    .commit();
        }
    }

    /*    View.OnClickListener tabClickListener = v -> {
        animateSliderTo(v);
//        binding.slider.setVisibility(View.GONE);
        binding.slider.setBackground(null);

        String selected = ((TextView) v).getText().toString();
        filter(selected); // Reuse your filter function
    };
    // Animation function
    private void animateSliderTo(View target) {
        int targetWidth = target.getWidth();

        binding.slider.animate()
                .x(target.getX())
                .setDuration(250)
                .setInterpolator((TimeInterpolator) new AccelerateDecelerateInterpolator())
                .start();

        // Animate width change
        ValueAnimator animator = ValueAnimator.ofInt(binding.slider.getWidth(), targetWidth);
        animator.setDuration(250);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            ViewGroup.LayoutParams layoutParams = binding.slider.getLayoutParams();
            layoutParams.width = (int) animation.getAnimatedValue();
            binding.slider.setLayoutParams(layoutParams);
        });
        animator.start();

    }

    private void filter(String type) {
        int width = binding.btnUnpaid.getWidth();
        binding.slider.getLayoutParams().width = width;
        binding.slider.setX(binding.btnUnpaid.getX());
        binding.slider.requestLayout();

        updateTabUI(type);

    }
    private void updateTabUI(String selectedTab) {
        binding.btnUnpaid.setBackgroundResource(selectedTab.equals("Unpaid") ? R.drawable.back_select : R.drawable.tab_unselected);
        binding.btnDraft.setBackgroundResource(selectedTab.equals("Draft") ? R.drawable.back_select : R.drawable.tab_unselected);
        binding.btnAll.setBackgroundResource(selectedTab.equals("All") ? R.drawable.back_select : R.drawable.tab_unselected);
    }*/
private void switchContentForTab(int tabId) {
    // Example: animate visibility or use ViewFlipper / FragmentTransaction
    if (tabId == R.id.tab_inbox) {
        // Show inbox layout
    } else if (tabId == R.id.tab_process) {
        // Show promotions layout
    }
}
    View.OnClickListener tabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < allTabs.size(); i++) {
                LinearLayout tab = allTabs.get(i);
                TextView label = allTextViews.get(i);
                ImageView icon = allIconViews.get(i);
                if (tab == v) {


                    label.setVisibility(View.VISIBLE);
                    label.setTextColor(Color.BLACK);
                    tab.setBackgroundResource(R.drawable.back_select);
                    selectedTab = tab;
                    icon.animate().alpha(true ? 1f : 0.5f).setDuration(200).start();

                } else {
                    label.setVisibility(View.GONE);
                    tab.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            int scrollX = v.getLeft() - (binding.tabScroll.getWidth() - v.getWidth()) / 2;
            binding.tabScroll.smoothScrollTo(scrollX, 0);

            // Call smooth animation or data update *after* UI changes
            switchContentForTab(v.getId());
        }
    };

    private void selectTab(LinearLayout tab, TextView label) {
        label.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(label, "alpha", 0f, 1f);
        animator.setDuration(300);
        animator.start();
    }

    private void deselectTab(LinearLayout tab, TextView label) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(label, "alpha", 1f, 0f);
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                label.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

}
