package com.oligark.flashapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ServiceViewHolder>{
    private final ServiceListCallback cb;
    private List<Service> serviceList;

    public ServiceListAdapter(List<Service> serviceList, ServiceListCallback cb) {
        if (serviceList == null) {
            this.serviceList = new ArrayList<>();
        } else {
            this.serviceList = serviceList;
        }
        this.cb = cb;
    }

    public interface ServiceListCallback {
        void serviceClick(Service service);
    }

    //@Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.service_listaservices, parent, false),
                cb
        );
    }

    //@Override
    public void onBindViewHolder(ServiceListAdapter.ServiceViewHolder holder, int position) {
        holder.bind(serviceList.get(position));
    }

    //@Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        private final TextView serviceDescriptionView;
        private final TextView serviceFechaInicioView;
        private final TextView serviceTipoView;
        private final TextView serviceFechaFinView;
        private final TextView serviceHoraInicioView;
        private final TextView serviceHoraFinView;
        private Service service;

        public ServiceViewHolder(View itemView, ServiceListAdapter.ServiceListCallback cb) {
            super(itemView);
            final ServiceListAdapter.ServiceListCallback callback = cb;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.serviceClick(service);
                }
            });
            serviceDescriptionView = itemView.findViewById(R.id.service_description);
            serviceFechaInicioView = itemView.findViewById(R.id.service_fecha_inicio);
            serviceTipoView = itemView.findViewById(R.id.service_tipo);
            serviceFechaFinView = itemView.findViewById(R.id.service_fecha_fin);
            serviceHoraInicioView = itemView.findViewById(R.id.service_hora_inicio);
            serviceHoraFinView = itemView.findViewById(R.id.service_hora_fin);
        }

        public void bind(Service service) {
            this.service = service;
            serviceDescriptionView.setText(service.getDescripcion());
            serviceFechaInicioView.setText(service.getFechaInicio());
            serviceTipoView.setText(service.getTipoServicio());
            serviceFechaFinView.setText(service.getFechaFin());
            serviceHoraInicioView.setText(service.getHoraInicio());
            serviceHoraFinView.setText(service.getHoraFin());
        }
    }
}
