package kg.geektech.hwfilmapiroom.ui.room;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.geektech.hwfilmapiroom.App;
import kg.geektech.hwfilmapiroom.R;
import kg.geektech.hwfilmapiroom.data.model.Films;
import kg.geektech.hwfilmapiroom.ui.film_list.adapter.FilmAdapter;

public class RoomFragment extends Fragment {

    private RecyclerView recyclerView;
    private FilmAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.filmRecyclerView);

        roomInit();

    }

    private void roomInit() {
        App.getAppDatabase().filmDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<Films>>() {
            @Override
            public void onChanged(List<Films> films) {
                initRecycler(films);
            }
        });
    }
    public void initRecycler(List<Films>list){
        adapter = new FilmAdapter();
        adapter.addItems(list);
        recyclerView.setAdapter(adapter);
    }
}
