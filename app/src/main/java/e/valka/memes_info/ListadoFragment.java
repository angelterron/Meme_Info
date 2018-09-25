package e.valka.memes_info;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListadoFragment extends Fragment {
    MemeTouchListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_listado, container, false);
    }
    public void setMemeTouchListener(MemeTouchListener l){
        listener = l;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        RecyclerView recyclerView = activity.findViewById (R.id.rvListado);
        if (recyclerView == null) return;

        String[] texto = getResources().getStringArray(R.array.names);
        TypedArray imagenes = getResources().obtainTypedArray(R.array.images);


        recyclerView.setLayoutManager (new GridLayoutManager(getContext (),2,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter (new  ListadoAdapter (getContext (), texto,imagenes,listener));
    }
}

class ListadoAdapter extends RecyclerView.Adapter<ListadoViewHolder> {
    private Context context;
    private String[] myData;
    private TypedArray imagenes;
    MemeTouchListener listener;

    ListadoAdapter (Context context, String[] myData,TypedArray imagenes, MemeTouchListener listener) {
        this.context = context;
        this.myData = myData;
        this.imagenes = imagenes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListadoViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from (context).inflate (R.layout.fragment_item, viewGroup, false);
        return new ListadoViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder (@NonNull ListadoViewHolder listadoViewHolder, int i) {
        listadoViewHolder.bind (myData[i],imagenes.getResourceId(i,-1));
        listadoViewHolder.itemView.setOnClickListener((view)->{
            if(listener != null) listener.onMemeTouched(i);
        });
    }

    @Override
    public int getItemCount () {
        return myData.length;
    }

}

class ListadoViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;

    ListadoViewHolder (@NonNull View itemView) {
        super (itemView);
        imageView = itemView.findViewById(R.id.imagen);
        textView = itemView.findViewById (R.id.textoImagen);
    }

    public void bind (String text, int imagen) {
        textView.setText (text);
        imageView.setImageResource(imagen);
    }
}
