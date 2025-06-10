import toast from 'react-hot-toast';
import { assets } from "../assets/assets";

import { useContext } from 'react';
import { AppContext } from '../context/AppContext';

const Header = () => {
  const { removeBg } = useContext(AppContext);

  return (
    <div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-12 items-center mb-16">
        {/* Left side: video banner */}
        <div className="order-2 md:order-1 flex justify-center">
          <div className="shadow-[0_25px_50px_-12px_rgba(0,0,0,0.15)] rounded-3xl overflow-hidden">
            <video
              src={assets.banner}
              autoPlay
              loop
              muted
              className="w-full max-w-[400px] h-auto object-cover"
            />
          </div>
        </div>

        {/* Right side: text content */}
        <div className="order-1 md:order-2 text-center md:text-left">
          <h1 className="text-3xl md:text-5xl font-bold mb-4">
            AI Background Remover
          </h1>
          <p className="text-lg text-gray-600 mb-4">
            Remove backgrounds from your images with just one click.
            Fast, clean, and powered by AI.
          </p>
          <p className="text-sm text-gray-500 italic mb-6">
            (The video solves it a little bit — follow the steps below to try yourself)
          </p>

          {/* Steps */}
          <ol className="list-decimal list-inside text-gray-700 mb-6 space-y-2">
            <li>Select an image from your device</li>
            <li>Upload the image using the button below</li>
            <li>Wait a few seconds while AI processes it</li>
            <li>Download your background-free image</li>
          </ol>

          <div>
            <input
              type="file"
              accept="image/*"
              id="upload1"
              hidden
              onChange={(e) => removeBg(e.target.files[0])}
            />
            <label
              htmlFor="upload1"
              className="bg-black text-white font-medium px-8 py-4 rounded-full hover:opacity-90 transition transform hover:scale-105 text-lg"
            >
              Upload your image
            </label>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;
