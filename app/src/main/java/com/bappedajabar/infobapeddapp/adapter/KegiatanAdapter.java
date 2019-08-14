package com.bappedajabar.infobapeddapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.activity.DetailActivity;
import com.bappedajabar.infobapeddapp.fragment.KegiatanFragment;
import com.bappedajabar.infobapeddapp.model.Kegiatan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.ListViewHolder> {
    private KegiatanFragment mContext;
    private Context context;
    private List<Kegiatan> kegiatanList;

    public KegiatanAdapter(KegiatanFragment kegiatanFragment, List<Kegiatan> kegiatanList) {
        this.mContext = mContext;
        this.kegiatanList = kegiatanList;
    }

    public List<Kegiatan> getKegiatanList() {
        return kegiatanList;
    }

    public void setKegiatanList(List<Kegiatan> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }

    public KegiatanAdapter(FragmentActivity activity) {
        this.context = activity;
    }


    public KegiatanAdapter (List<Kegiatan> kegiatanList){
        this.kegiatanList = kegiatanList;
    }
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanAdapter.ListViewHolder holder, int position) {
        final Kegiatan p = getKegiatanList().get(position);
        holder.tvId.setText(p.getNamaKegiatan());
        holder.tvTanggal.setText(p.getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext.getActivity(), DetailActivity.class);
                i.putExtra("nama",p.getNamaKegiatan());
                i.putExtra("waktu",p.getWaktuMulai());
                i.putExtra("waktus",p.getWaktuSelesai());
                i.putExtra("tanggal",p.getTanggal());
                i.putExtra("tempat",p.getTempat());
                i.putExtra("asalsurat",p.getAsalsurat());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getActivity().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getKegiatanList().size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_judul)
        TextView tvId;
        @BindView(R.id.tv_jam)
        TextView tvTanggal;


        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
