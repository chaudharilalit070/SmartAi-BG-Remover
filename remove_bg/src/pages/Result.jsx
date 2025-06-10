import { useContext } from "react";
import { AppContext } from "../context/AppContext";
import { useNavigate } from "react-router-dom";
import { assets } from "../assets/assets";

const Result = () => {
  const navigate = useNavigate();
  const { image, resultImage } = useContext(AppContext);

  return (
    <div className="mt-14 mb-3 mx-4 lg:mx-44 min-h-[75vh]">
      {/* image container */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {/* left side */}
        <div className="flex flex-col space-y-4">
          <p className="font-semibold text-gray-600 mb-2">Original</p>
          <img
            src={image ? URL.createObjectURL(image) : ""}
            alt="original"
            className="rounded-md w-full object-cover shadow-md"
          />
        </div>

        {/* right side */}
        <div className="flex flex-col space-y-4">
          <p className="font-semibold text-gray-600 mb-2">Background Removed</p>
          <div className="relative rounded-md border border-gray-300 h-full bg-layer overflow-hidden">
            <img
              src={resultImage ? resultImage : ""}
              alt="result"
              className="w-full object-cover shadow-md"
            />

            {!resultImage && image && (
              <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin"></div>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* buttons */}
      {resultImage && (
        <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
          <button
            className="border border-indigo-600 text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-all duration-300"
            onClick={() => navigate("/")}
          >
            Try another image
          </button>

          <a
            href={resultImage}
            download
            className="cursor-pointer py-3 px-6 text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition duration-300 ease-in-out transform hover:scale-105"
          >
            Download image
          </a>
        </div>
      )}
    </div>
  );
};

export default Result;
