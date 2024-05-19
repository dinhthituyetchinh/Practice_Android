package apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day11.Employee;
import com.example.day11.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{


    private final Context context;
    private final List<Employee> items;

    public RecyclerViewAdapter(Context context, List<Employee> items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View layout = LayoutInflater.from(context)
                .inflate((R.layout.list_item_member), parent, false);
        viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee item = items.get(position);
        holder.id.setText(item.getId());
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, name, email, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtID);
            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            delete = itemView.findViewById(R.id.txtDelete);
        }
    }
}
