    package com.example.pum1

    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView

    class DogAdapter(
        private val context: Context,
        private val dogs: List<DogBreed>
    ) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
            return DogViewHolder(view)
        }

        override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
            val dog = dogs[position]
            holder.dogName.text = dog.name

            // Load image from drawable resources
            val imageResId = context.resources.getIdentifier(dog.imageURL, "drawable", context.packageName)
            if (imageResId != 0) {
                holder.dogImage.setImageResource(imageResId)
            } else {
                // Set a default image if the drawable is not found
                holder.dogImage.setImageResource(R.drawable.default_image)
            }
        }

        override fun getItemCount(): Int {
            return dogs.size
        }

        class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val dogName: TextView = itemView.findViewById(R.id.dogName)
            val dogImage: ImageView = itemView.findViewById(R.id.dogImage)
        }
    }