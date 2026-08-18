package bd.com.ashfaq.jibon_bhai;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new InboxFragment();
            case 1: return new GroupsFragment();
            case 2: return new DashFragment();
            case 3: return new ProfileFragment();
            default: return new InboxFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Total 4 swipeable tabs
    }
}