package kg.geektech.hwfilmapiroom.ui.film_list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.hwfilmapiroom.R;
import kg.geektech.hwfilmapiroom.data.model.Films;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    List<Films> list = new ArrayList<>();
    private Callback callback;
    private SaveRoom saveRoom;

    public FilmAdapter(Callback callback, SaveRoom saveRoom) {
        this.callback = callback;
        this.saveRoom = saveRoom;
    }

    public FilmAdapter(){

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list, parent, false);

        return new ViewHolder(view,callback,saveRoom);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Films>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView producer;
        private Callback callback;
        private ImageView imageView;
        private SaveRoom saveRoom;

        public ViewHolder(@NonNull View itemView,Callback callback,SaveRoom saveRoom) {
            super(itemView);
            this.saveRoom = saveRoom;
            this.callback = callback;
            title = itemView.findViewById(R.id.text_title_film);
            producer = itemView.findViewById(R.id.text_director_film);
            imageView = itemView.findViewById(R.id.image_save_to_room);
        }

        public void onBind(Films films) {
            title.setText(films.getTitle());
            producer.setText(films.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.filmClick(films);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveRoom.click(films);
                }
            });
        }
    }

    public interface Callback {
        void filmClick(Films films);
    }
    public interface SaveRoom{
        void click(Films films);
    }
}
