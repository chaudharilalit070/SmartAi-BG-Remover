  
import { useState } from "react";
import { category } from "../assets/assets";
import { assets } from "../assets/assets";
const BgSlider = () => {
  const [sliderPosition, setSliderPosition] = useState(50);
  const [activeCategory, setActiveCategory] = useState("People");

  const handleSliderChanges = (e) => {
    setSliderPosition(e.target.value);
  };

  return (
    <div className="relative w-full max-w-4xl mx-auto py-8">
      {/* Title */}
      <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-6 text-center">
        Stunning quality.
      </h2>

      {/* Nav bar / category selector */}
      <div className="flex justify-center mb-10 flex-wrap">
        <div className="inline-flex gap-4 bg-gray-100 p-2 rounded-full flex-wrap justify-center">
          {category.map((cat) => (
            <button
              key={cat}
              className={`px-6 py-2 rounded-full font-medium transition ${
                activeCategory === cat
                  ? "bg-white text-gray-800 shadow-sm"
                  : "text-gray-600 hover:bg-gray-200"
              }`}
              onClick={() => setActiveCategory(cat)}
            >
              {cat}
            </button>
          ))}
        </div>
      </div>

      {/* Image comparison slider */}
      <div className="relative w-full max-w-4xl max-h-[500px] overflow-hidden m-auto rounded-xl shadow-lg">
        <img
          src={assets.imgslider}
          alt="original img"
          style={{
            clipPath: `inset(0 ${100 - sliderPosition}% 0 0)`,
            maxWidth: "100%",
            maxHeight: "500px",
            objectFit: "cover",
            display: "block",
          }}
          className="w-full h-full"
        />
        <img
          src={assets.imgslider2}
          alt="remove background image"
          style={{
            clipPath: `inset(0 0 0 ${sliderPosition}%)`,
            maxWidth: "100%",
            maxHeight: "500px",
            objectFit: "cover",
            display: "block",
          }}
          className="absolute top-0 left-0 w-full h-full"
        />
        <input
          type="range"
          className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-full z-20 appearance-none h-2 bg-transparent slider"
          min={0}
          max={100}
          onChange={handleSliderChanges}
          value={sliderPosition}
        />
      </div>

     
    </div>
  );
};

export default BgSlider;


