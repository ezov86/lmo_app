package com.komiac.lmo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.komiac.lmo.data.PrescriptionOverview;
import com.komiac.lmo.data.PrescriptionType;
import com.komiac.lmo.data.PrescriptionsRepository;
import com.komiac.lmo.factories.PrescriptionsFragmentFactory;
import com.komiac.lmo.fragments.InfoFragment;
import com.komiac.lmo.fragments.PagesFragment;
import com.komiac.lmo.fragments.ReleasedFragment;


public class MainActivity extends AppCompatActivity {
    private TextView toolbarTitle;
    private FragmentContainerView fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrescriptionsRepository repository = new PrescriptionsRepository();
        repository.update();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.setFragmentFactory(new PrescriptionsFragmentFactory(repository,
                onSelect));
        fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(onBackClick);

        toolbarTitle = findViewById(R.id.toolbarTitle);

        fragmentContainer = findViewById(R.id.fragmentContainer);

        if (savedInstanceState == null)
            changeFragment(PagesFragment.class, null);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            super.onBackPressed();
        else
            finish();
    }

    private void changeFragment(Class<? extends Fragment> fragmentClass, Bundle args) {
        String tag = fragmentClass.getName();

        updateTitle(fragmentClass);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.fade_out
                )
                .replace(R.id.fragmentContainer, fragmentClass, args, tag)
                .addToBackStack(tag)
                .setReorderingAllowed(true)
                .commit();
    }

    private void updateTitle(Class<? extends Fragment> fragmentClass) {
        toolbarTitle.setText(getFragmentTitle(fragmentClass));
    }

    private String getFragmentTitle(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == PagesFragment.class)
            return getString(R.string.prescriptions);
        else if (fragmentClass == InfoFragment.class)
            return getString(R.string.info);

        return "";
    }


    // Listeners.
    private final View.OnClickListener onBackClick = (View v) -> onBackPressed();

    private final OnSelectListener onSelect = (PrescriptionOverview prescription) -> {
        Class<? extends Fragment> fragmentClass;
        if (prescription.type == PrescriptionType.Released)
            fragmentClass = ReleasedFragment.class;
        else
            fragmentClass = InfoFragment.class;

        Bundle bundle = new Bundle();
        bundle.putLong("id", prescription.getFullPrescription().getId());

        changeFragment(fragmentClass, bundle);
    };

    private final FragmentManager.OnBackStackChangedListener onBackStackChangedListener = () -> {
        updateTitle(fragmentContainer.getFragment().getClass());
    };
}