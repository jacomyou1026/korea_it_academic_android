import React from "react";

import Icon from "./Icon";

import type { IconProps } from "@_types/icon-type";

const LanguageIcon: React.FC<IconProps> = React.memo((props) => (
  <Icon {...props}>
    <svg viewBox="0 0 28 28" xmlns="http://www.w3.org/2000/svg">
      <path d="M13.001 2C12.4488 2 12.001 2.44772 12.001 3C12.001 3.55228 12.4488 4 13.001 4H18V6.5C18 7.32764 17.6841 7.91673 17.1997 8.317C16.6944 8.73446 15.9353 9.00032 14.9995 9.00032C14.4472 9.00032 13.9995 9.44803 13.9995 10.0003C13.9995 10.5526 14.4472 11.0003 14.9995 11.0003C16.2953 11.0003 17.5365 10.633 18.4735 9.85884C19.4314 9.06745 20 7.90638 20 6.5V3C20 2.44772 19.5523 2 19 2H13.001Z" />
      <path d="M10.928 7.61492C10.7737 7.24021 10.4085 6.99565 10.0033 6.99563C9.5981 6.9956 9.23292 7.24013 9.07861 7.61481L2.07537 24.6193C1.86505 25.13 2.10853 25.7144 2.6192 25.9247C3.12987 26.1351 3.71435 25.8916 3.92467 25.3809L5.7324 20.9916H14.2724L16.0795 25.3807C16.2898 25.8914 16.8742 26.135 17.3849 25.9247C17.8956 25.7145 18.1392 25.13 17.9289 24.6193L15.9691 19.8593C15.9459 19.6836 15.8771 19.5224 15.775 19.3878L10.928 7.61492ZM13.449 18.9916H6.55609L10.0031 10.6219L13.449 18.9916Z" />
      <path d="M22 2C22.5523 2 23 2.44772 23 3V8H25C25.5523 8 26 8.44772 26 9C26 9.55228 25.5523 10 25 10H23V19.0005C23 19.5528 22.5523 20.0005 22 20.0005C21.4477 20.0005 21 19.5528 21 19.0005V3C21 2.44772 21.4477 2 22 2Z" />
    </svg>
  </Icon>
));

export default LanguageIcon;
