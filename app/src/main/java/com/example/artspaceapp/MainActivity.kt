package com.example.artspaceapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentImageIndex = 0

    // List of artworks (added descriptions)
    private val artworks = listOf(
        Artwork(R.drawable.la_pieta,"La Pietà","Michelangelo","The Pietà, a form of the Lamentation of Christ where the Virgin Mary is seen to be mourning Christ alone."),
        Artwork(R.drawable.the_descent_from_the_cross,"The Descent from the Cross","Jacopo Tintoretto","Depictions of Christ carrying the cross, being crucified and descending from it are extensive within Western artwork."),
        Artwork(R.drawable.david_and_goliath,"David and Goliath","Tiziano Vecellio","The story of the shepherd boy David defeating the giant Goliath in single combat using only a slingshot."),
        Artwork(R.drawable.the_creation_of_adam, "The Creation of Adam", "Michelangelo", "Starting right at the beginning is the creation of Adam and Eve."),
        Artwork(R.drawable.the_last_super, "The Last Supper", "Leonardo Da Vinci", "A depiction of Christ's final meal with his apostles before his crucifixion."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // New TextView for artwork description
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Display initial artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)

        // Set up button click listeners
        previousButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                artworks.size - 1
            }
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % artworks.size
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }
    }

    // Function to display the current artwork
    private fun displayArtwork(
        imageView: ImageView,
        artTitle: TextView,
        artistName: TextView,
        artDescription: TextView // Add the new TextView for description
    ) {
        val artwork = artworks[currentImageIndex]
        imageView.setImageResource(artwork.imageResId)
        artTitle.text = artwork.title
        artistName.text = artwork.artist
        artDescription.text = artwork.description
    }

    // Handle configuration changes such as orientation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Re-apply the current artwork to the new layout
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // Re-add description TextView

        // Display the current artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
    }

    // Define your Artwork data class
    data class Artwork(
        val imageResId: Int,
        val title: String,
        val artist: String,
        val description: String // Add description field to the Artwork data class
    )
}
