package com.komiac.lmo.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.komiac.lmo.fragments.PageFragment;

import java.util.ArrayList;

public class PageFragmentAdapter extends FragmentStateAdapter {
    private final ArrayList<PageFragment> pages;

    public PageFragmentAdapter(FragmentActivity activity,
                               ArrayList<PageFragment> pages) {
        super(activity);
        this.pages = pages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PageFragment oldPage = pages.get(position);

        PageFragment page = new PageFragment();
        page.setRepository(oldPage.getRepository());
        page.setPageType(oldPage.getPageType());
        page.setOnSelect(oldPage.getOnSelect());

        pages.set(position, page);

        return page;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}
