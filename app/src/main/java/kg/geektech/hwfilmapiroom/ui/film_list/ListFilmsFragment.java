package kg.geektech.hwfilmapiroom.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.geektech.hwfilmapiroom.App;
import kg.geektech.hwfilmapiroom.R;
import kg.geektech.hwfilmapiroom.data.model.Films;
import kg.geektech.hwfilmapiroom.remote.RetrofitBuilder;
import kg.geektech.hwfilmapiroom.ui.film_list.adapter.FilmAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFilmsFragment extends Fragment implements FilmAdapter.Callback, FilmAdapter.SaveRoom {

    private final FilmAdapter adapter = new FilmAdapter(this,this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_films, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if (response.isSuccessful())
                adapter.addItems(response.body());
                Log.e("tag","success: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Log.e("tag","success:" + t.getLocalizedMessage());
            }
        });
    }

    private void initView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void filmClick(Films films) {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment);
        Bundle bundle = new Bundle();
        bundle.putString("id",films.getId());
        navController.navigate(R.id.detailFragment,bundle);
    }

    @Override
    public void click(Films films) {
        App.getAppDatabase().filmDao().insert(new Films(films.getTitle(),films.getProducer()));
    }
}