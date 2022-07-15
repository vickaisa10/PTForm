package com.example.ptform.BD;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ptform.Maps;
import com.example.ptform.R;

import java.util.ArrayList;



public class Adaptador_Lista extends RecyclerView.Adapter<Adaptador_Lista.PeliculasViewHolder> {

    private ArrayList<BD_Listas> Listas;
    private Context mCtx;

    public Adaptador_Lista(Context mCtx, ArrayList<BD_Listas> mostrarListas) {
        this.mCtx = mCtx;
        this.Listas = mostrarListas;
    }


    @Override
    public PeliculasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_registro, null, false);
        return new  PeliculasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final PeliculasViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        //Vista de los datos en el recyclerview
        holder.txtelabora.setText(Listas.get(position).getElabora());
        holder.txtproyecto1.setText(Listas.get(position).getProyecto1());
        holder.txtfecha.setText(Listas.get(position).getFecha());
        holder.txtexpediente.setText(Listas.get(position).getExpediente());
        holder.txtoperador.setText(Listas.get(position).getOperador());
        holder.txtproyecto.setText(Listas.get(position).getProyecto());
        holder.txttipoInfraestructura.setText(Listas.get(position).getTipoInfraestructura());
        holder.txtidInfraestructura.setText(Listas.get(position).getIdInfraestructura());
        holder.txtcaracteristicas.setText(Listas.get(position).getCaracteristicas());
        holder.txtestado.setText(Listas.get(position).getEstado());
        holder.txtadecuacion.setText(Listas.get(position).getAdecuacion());
        holder.txttipoAdecuacion.setText(Listas.get(position).getTipoAdecuacion());
        holder.txtcalidad.setText(Listas.get(position).getCalidad());
        holder.txtobservaciones.setText(Listas.get(position).getObservaciones());
        holder.txtesteCoordenada.setText(Listas.get(position).getEsteCoordenada());
        holder.txtnorteCoordenada.setText(Listas.get(position).getNorteCoordenada());

        holder.btnMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, Maps.class);
                intent.putExtra("este",Listas.get(position).getEsteCoordenada());
                intent.putExtra("norte",Listas.get(position).getNorteCoordenada());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

   return Listas.size();

    }
    public class PeliculasViewHolder extends RecyclerView.ViewHolder {

          TextView txtelabora,txtproyecto1 ,  txtfecha , txtexpediente , txtoperador , txtproyecto , txttipoInfraestructura , txtidInfraestructura , txtcaracteristicas
                  , txtestado , txtadecuacion , txttipoAdecuacion, txtcalidad, txtobservaciones, txtesteCoordenada, txtnorteCoordenada;

          Button btnMapas ;

        public PeliculasViewHolder(final View itemView) {
            super(itemView);

            txtelabora = itemView.findViewById(R.id.txtelabora);
            txtproyecto1= itemView.findViewById(R.id.txtproyecto1);
            txtfecha= itemView.findViewById(R.id.txtfecha);
            txtexpediente = itemView.findViewById(R.id.txtexpediente);
            txtoperador = itemView.findViewById(R.id.txtoperador);
            txtproyecto = itemView.findViewById(R.id.txtproyecto);
            txttipoInfraestructura = itemView.findViewById(R.id.txttipoInfraestructura);
            txtidInfraestructura= itemView.findViewById(R.id.txtidInfraestructura);
            txtcaracteristicas = itemView.findViewById(R.id.txtcaracteristicas);
            txtestado = itemView.findViewById(R.id.txtestado);
            txtadecuacion= itemView.findViewById(R.id.txtadecuacion);
            txttipoAdecuacion= itemView.findViewById(R.id.txttipoAdecuacion);
            txtcalidad = itemView.findViewById(R.id.txtcalidad);
            txtobservaciones = itemView.findViewById(R.id.txtobservaciones);
            txtesteCoordenada = itemView.findViewById(R.id.txtesteCoordenada);
            txtnorteCoordenada = itemView.findViewById(R.id.txtnorteCoordenada);
            btnMapas = itemView.findViewById(R.id.btnMapas);

        }

    }

}
