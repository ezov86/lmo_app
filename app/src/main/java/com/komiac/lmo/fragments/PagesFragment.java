package com.komiac.lmo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.komiac.lmo.OnSelectListener;
import com.komiac.lmo.R;
import com.komiac.lmo.adapters.PageFragmentAdapter;
import com.komiac.lmo.data.PrescriptionType;
import com.komiac.lmo.data.PrescriptionsRepository;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PagesFragment extends Fragment implements FragmentWithPrescriptionRepository,
        OnSelectableFragment {
    private PrescriptionsRepository repository;
    private PageFragmentAdapter adapter;
    private ArrayList<PageFragment> pages;
    private OnSelectListener onSelect;

    private SwipeRefreshLayout swipeRefresh;

    public PagesFragment() {
        super(R.layout.fragment_pages);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_pages,
                container,
                false
        );

        initPages();

        // Views.
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        adapter = new PageFragmentAdapter(requireActivity(), pages);
        viewPager.setAdapter(adapter);

        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(refresh);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, tabConfigStrategy).attach();

        return view;
    }

    public void initPages() {
        pages = new ArrayList<>();

        for (PrescriptionType type : PrescriptionType.values()) {
            PageFragment page = new PageFragment();
            page.setPageType(type);
            page.setRepository(repository);
            page.setOnSelect(onSelect);
            pages.add(page);
        }
    }


    // Setters & getters.
    @Override
    public void setRepository(PrescriptionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setOnSelectListener(OnSelectListener listener) {
        this.onSelect = listener;
    }


    // Listeners.
    private final SwipeRefreshLayout.OnRefreshListener refresh = () -> {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            repository.update();

            handler.post(() -> {
                for (int i = 0; i < pages.size(); i++) {
                    PageFragment page = pages.get(i);
                    if (!page.isInit())
                        continue;

                    page.update();
                    adapter.notifyItemChanged(i);
                }

                swipeRefresh.setRefreshing(false);
            });
        });
    };

    private final TabLayoutMediator.TabConfigurationStrategy tabConfigStrategy = (tab, position) -> {
        int strId;

        switch (PrescriptionType.values()[position]) {
            case Prescribed:
                strId = R.string.prescribed;
                break;

            case Released:
                strId = R.string.released;
                break;

            case Delayed:
            default:
                strId = R.string.delayed;
                break;
        }

        tab.setText(getString(strId));
    };


}