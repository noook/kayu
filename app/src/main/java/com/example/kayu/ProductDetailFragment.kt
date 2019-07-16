package com.example.kayu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_detail.view.*


private const val ARG_PRODUCT = "product"

class ProductDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(product: Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val arguments = Bundle()
            arguments.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments!!.getParcelable<Product>(ARG_PRODUCT)!!

        view.product_title.text = product.name
        view.product_brand.text = product.brand
        view.barcode.setTitleValue(
            view.context.getString(R.string.barcode),
            product.barcode
        )
        view.quantity.setTitleValue(
            view.context.getString(R.string.quantity),
            product.quantity
        )
        view.sold_in.setTitleValue(
            view.context.getString(R.string.sold_in),
            if (product.soldIn.isEmpty()) view.context.getString(R.string.sold_nowhere) else product.soldIn.joinToString(
                separator = ", "
            )
        )
        view.ingredients.setTitleValue(
            view.context.getString(R.string.ingredients),
            product.ingredients.joinToString(separator = ", ")
        )
        view.allergenes.setTitleValue(
            view.context.getString(R.string.allergenes),
            if (product.allergenes.isEmpty()) view.context.getString(R.string.no_allergenes) else product.allergenes.joinToString(
                separator = ", "
            )
        )
        view.label_additives.setTitleValue(
            view.context.getString(R.string.additives),
            if (product.additives.isEmpty()) view.context.getString(R.string.no_additives) else product.additives.joinToString(
                separator = ", "
            )
        )
        view.nutri_score.setImageResource(
            resources.getIdentifier(
                "nutri_score_${product.nutriscore.toLowerCase()}",
                "drawable",
                view.context.packageName
            )
        )
        Picasso.get().load(product.picUrl).into(view.item_picture)
    }
}
