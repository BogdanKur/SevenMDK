package com.pet.sevenmdk

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.pet.sevenmdk.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment() {
    private lateinit var binding: FragmentAnimationBinding
    private var isPlay = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                bottom = rect.bottom + insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            )
            insets
        }
        binding.songBtn.setOnClickListener {
            if (binding.animated.isAnimating) {
                binding.animated.cancelAnimation()
            }

            binding.animated.playAnimation()
            rotateImage(it as ImageView)
        }
    }

    private fun rotateImage(imageView: ImageView) {
        val rotateAnimation = ObjectAnimator.ofFloat(
            imageView,
            "rotation",
            0f,
            360f
        ).apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
        }

        rotateAnimation.start()
    }
}