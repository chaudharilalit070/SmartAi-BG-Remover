
import { footer } from '../assets/assets';
// import logo from '../assets/logo.png';
import { assets } from "../assets/assets";

const Footer = () => {
  return (
    <footer className="flex flex-col sm:flex-row items-center justify-between gap-4 px-6 lg:px-44 py-4 bg-gray-50 border-t border-gray-200">
      {/* Logo */}
      <img src={assets.logo} alt="logo" width={40} className="mb-2 sm:mb-0" />

      {/* Copyright */}
      <p className="flex-1 border-l border-gray-300 pl-4 text-gray-600 max-sm:hidden text-sm select-none">
        &copy; {new Date().getFullYear()} @RC || All rights reserved
      </p>

      {/* Social Icons */}
      <div className="flex gap-5">
        {footer.map((item, index) => (
          <a
            href={item.url}
            key={index}
            target="_blank"
            rel="noopener noreferrer"
            className="transform transition-transform hover:scale-110"
            aria-label={`Link to ${item.url.replace(/^https?:\/\//, '')}`}
          >
            <img src={item.logo} alt="social icon" width={32} height={32} />
          </a>
        ))}
      </div>
    </footer>
  );
};

export default Footer;
