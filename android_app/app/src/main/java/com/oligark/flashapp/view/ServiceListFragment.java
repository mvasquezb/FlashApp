package com.oligark.flashapp.view;

        import android.arch.lifecycle.Observer;
        import android.arch.lifecycle.ViewModelProviders;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.oligark.flashapp.R;
        import com.oligark.flashapp.di.Dependencies;
        import com.oligark.flashapp.model.Service;
        import com.oligark.flashapp.view.adapter.ServiceListAdapter;
        import com.oligark.flashapp.viewmodel.ServiceListViewModel;
        import java.util.List;


public class ServiceListFragment extends Fragment implements ServiceListAdapter.ServiceListCallback {

    private  LinearLayoutManager layoutManager;
    private ServiceListViewModel viewModel;
    private ServiceListAdapter serviceListAdapter;
    private RecyclerView rv;

//    private OnFragmentInteractionListener mListener;

    public ServiceListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ServiceListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service_list, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.serviceListView);
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());

        viewModel.getServiceList().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(@Nullable List<Service> services) {
                serviceListAdapter = new ServiceListAdapter(services, ServiceListFragment.this);
                rv.setAdapter(serviceListAdapter);
            }
        });
        rv.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void serviceClick(Service service) {
        ServiceDetailFragment serviceDetailFragment = new ServiceDetailFragment();
        Bundle data = new Bundle();
        data.putString("serviceJson", Dependencies.getInstance().gson.toJson(service));
        serviceDetailFragment.setArguments(data);
        ((CustomerMainActivity) getActivity()).replaceFragment(serviceDetailFragment, true);

    }
}
