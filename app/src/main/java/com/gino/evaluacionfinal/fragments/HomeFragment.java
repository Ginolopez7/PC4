package com.gino.evaluacionfinal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gino.evaluacionfinal.data.response.ShowResponse;
import com.gino.evaluacionfinal.data.retrofit.RetrofitHelper;
import com.gino.evaluacionfinal.databinding.FragmentHomeBinding;
import com.gino.evaluacionfinal.model.Sagas;
import com.gino.evaluacionfinal.model.Shows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitHelper.getService().getShows().enqueue(new Callback<ShowResponse>() {
            @Override
            public void onResponse(Call<ShowResponse> call, Response<ShowResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    showSagas(response.body().getShowsList());
                }
            }

            @Override
            public void onFailure(Call<ShowResponse> call, Throwable t) {

            }
        });
        homeViewModel.listLiveData.observe(requireActivity(), showList -> {
            // Mostrar en recycle
        });
        homeViewModel.getShows();
    }

    private void showSagas(List<Shows> showList) {
        RVShowAdapter adapter = new RVShowAdapter(showList, show -> {
            homeViewModel.addShow(show);
        });
        binding.rvShows.setAdapter(adapter);
    }

    private List<Shows> getData(){
        List<Shows> shows = new ArrayList<>();
        shows.add(new Sagas("Saga Dragon ball","https://i.pinimg.com/736x/b4/80/22/b48022dd46917dc87214ea57b617a213.jpg","153 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga de Frezzer","https://i.pinimg.com/1200x/37/39/d7/3739d77766933b392e556432e8bc5fef.jpg","72 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga de Cell","https://krakenperu.com/wp-content/uploads/2019/05/94652-FP4094-Dragon-Ball-Z-Cell-Saga.jpg","72 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga de Mayin Boo","https://i.pinimg.com/originals/8a/a2/e4/8aa2e46c01ae0c287901c25449979902.jpg","90 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga Batalla de los Dioses","https://i0.wp.com/www.lacasadeel.net/wp-content/uploads/2022/08/Dragon-Ball-Super-la-Batalla-de-los-dioses.jpg?resize=419%2C593.jpg","72 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga Torneo de la Fuerza","https://i.pinimg.com/736x/03/5b/a3/035ba3c0e13e1232834758f58b39987a.jpg","14 ep","Anime. Drama. Accion"));
        shows.add(new Sagas("Saga Dragon Ball GT","https://glbes.dokkaninfo.com/assets/global/es/ingame/events/es/quest_top_banner_357_A.png","65 ep","Anime. Drama. Accion"));
        return shows;
    }
}