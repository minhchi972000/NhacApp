package com.example.animationappnhac.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.animationappnhac.R
import com.example.animationappnhac.adapter.AdapterSong
import com.example.animationappnhac.databinding.SongListBinding
import com.example.animationappnhac.model.Song


class SongFragment : Fragment() {

    lateinit var vb: SongListBinding
    var arraySong: ArrayList<Song> = ArrayList()
    var adapterSong = AdapterSong()

    var index: Int = 0

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5

    var currentPosition: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = SongListBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AddSong()
        setUpSongList()
       
        vb.includeSwipeBottom.motionSwipe.addTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when(currentId){
                    R.id.nextHide->{
                        vb.includeSwipeBottom.viewSwipeRight.translationZ = 100f
                        vb.includeSwipeBottom.viewSwipeLeft.translationZ = -100f
                    }
                    R.id.backHide->{
                        vb.includeSwipeBottom.viewSwipeLeft.translationZ = 100f
                        vb.includeSwipeBottom.viewSwipeRight.translationZ = -100f
                    }
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }
        })
    }

    private fun setUpSongList() {
        vb.recyclerView.adapter = adapterSong
        val pager = PagerSnapHelper()
        pager.attachToRecyclerView(vb.recyclerView)
        adapterSong.setData(arraySong)
        adapterSong.notifyDataSetChanged()

        vb.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                Log.d("AAA", "currention: ${currentPosition}")
            }
        })


    }

    private fun AddSong() {
        arraySong.add(Song("Đế vương", "Sơn tùng", R.raw.de_vuong, R.drawable.sontung))
        arraySong.add(
            Song(
                "Hai triệu năm",
                "Phan mạnh quỳnh",
                R.raw.hai_trieu_nam,
                R.drawable.phanmanhquynh
            )
        ) //
        arraySong.add(Song("Cưới thôi", "Sơn tùng", R.raw.cuoi_thoi, R.drawable.sontung))
        arraySong.add(Song("Độ tộc 2", "Phan mạnh quỳnh", R.raw.do_toc2, R.drawable.phanmanhquynh1))
        arraySong.add(
            Song(
                "Kẻ cắp gặp bà già",
                "Sơn tùng",
                R.raw.ke_cap_gap_ba_gia,
                R.drawable.sontung
            )
        )
        arraySong.add(
            Song(
                "Muộn rồi sao còn",
                "Phan mạnh quỳnh",
                R.raw.muon_roi_sao_con,
                R.drawable.phanmanhquynh2
            )
        )
        arraySong.add(
            Song(
                "Sài gòn đau lòng quá",
                "Phan mạnh quỳnh",
                R.raw.sai_gon_dau_long_qua,
                R.drawable.phanmanhquynh1
            )
        )
        arraySong.add(Song("So fa", "Sơn tùng", R.raw.so_far, R.drawable.sontung))
    }


}